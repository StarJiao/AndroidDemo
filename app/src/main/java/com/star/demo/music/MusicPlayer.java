package com.star.demo.music;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import com.star.demo.R;

import java.io.File;
import java.io.IOException;

public class MusicPlayer {
    private final String TAG = MusicPlayer.class.getSimpleName();

    private static MusicPlayer instance;
    private MediaPlayer mediaPlayer;
    private Handler processHandler;

    private View musicLayout;
    private TextView musicName;
    private TextView musicSinger;
    private SeekBar musicProgress;
    private ProgressBar updateProgress;
    private ImageView playButton;
    private ImageView previousButton;
    private ImageView nextButton;

    public static final int WHAT_BUFF_PROGRESS = 0;
    public static final int WHAT_PLAY_PROGRESS = 1;

    private boolean finishUpdate = false;
    private boolean hasView = false;

    private MusicPlayer() {
        try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setLooping(false);
            mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
                @Override
                public void onBufferingUpdate(MediaPlayer mp, int percent) {
                    if (!finishUpdate) {
                        Log.i(TAG, percent + "% buffer");
                        if (hasView) {
                            sendMessage(WHAT_BUFF_PROGRESS, percent);
                            if (percent == 100) {
                                updateProgress.setVisibility(View.INVISIBLE);
                            }
                        }
                    }
                    if (percent == 100) {
                        finishUpdate = true;
                    }
                }
            });
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    Log.i(TAG, "onPrepared");
                    mp.start();
                    if (hasView) {
                        processHandler.postDelayed(runnable, 500);
                    }
                }
            });
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    Log.i(TAG, "Music onCompletion");
                    if (hasView) {
                        processHandler.removeCallbacks(runnable);
                    }
                }
            });
        } catch (Exception e) {
            Log.d(TAG, "error", e);
        }
    }

    public static MusicPlayer getInstance() {
        if (null == instance) {
            instance = new MusicPlayer();
        }
        return instance;
    }

    //适用于无界面播放本地mp3文件
    public void playLocalFile(File file) {
        initState(true, false);
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(file.getAbsolutePath());
            mediaPlayer.prepare();
            Log.i(TAG, "file to play: " + file.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //适用于无界面播放本地Assert文件，无界面
    public void playAssertFile(AssetFileDescriptor fileDescriptor) {
        initState(true, false);
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(fileDescriptor.getFileDescriptor(), fileDescriptor.getStartOffset(), fileDescriptor.getLength());
            fileDescriptor.close();
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //适用于播放从讯飞的音乐库中拿到的云端音乐，可提供界面
    public void playMusic(String url) {
        initState(false, true);
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //适用于播放铃声、通知、等本地系统文件
    public void playUri(Context context, Uri uri) {
        initState(true, false);
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(context, uri);
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initState(boolean finishUpdate, boolean hasView) {
        this.finishUpdate = finishUpdate;
        this.hasView = hasView;
    }

    private void resume() {
        if (null != mediaPlayer) {
            mediaPlayer.start();
            if (hasView) {
                processHandler.postDelayed(runnable, 500);
                playButton.setImageResource(R.drawable.music_pause_button);
            }
        }
    }

    private void pause() {
        if (null != mediaPlayer) {
            mediaPlayer.pause();
            if (hasView) {
                processHandler.removeCallbacks(runnable);
                playButton.setImageResource(R.drawable.music_play_button);
            }
        }
    }

    public void stop() {
        if (hasView) {
            processHandler.removeCallbacks(runnable);
        }
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

    public void destroy() {
        if (hasView) {
            processHandler.removeCallbacks(runnable);
        }
        if (null != mediaPlayer) {
            mediaPlayer.release();
        }
    }

    public boolean isPlaying() {
        if (null != mediaPlayer) {
            return mediaPlayer.isPlaying();
        }
        return false;
    }

    public int getPercent() {
        if (null != mediaPlayer) {
            double percent = mediaPlayer.getCurrentPosition() * 1.0 / mediaPlayer.getDuration();
            if (percent > 0.99) {
                percent = 1;
            }
            return (int) (100 * percent);
        }
        return -1;
    }

    private void resumeOrPause() {
        if (isPlaying()) {
            pause();
        } else {
            resume();
        }
    }

    public void playNext() {
        mediaPlayer.seekTo(0);
        if (hasView) {
            processHandler.removeCallbacks(runnable);
            processHandler.postDelayed(runnable, 500);
            resume();
        }
    }

    public void playPrevious() {
        mediaPlayer.seekTo(0);
        if (hasView) {
            processHandler.removeCallbacks(runnable);
            processHandler.postDelayed(runnable, 500);
            resume();
        }
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            int percent = getPercent();
            Log.i(TAG, "percent:" + percent);
            if (percent < 99 && percent >= 0) {
                sendMessage(WHAT_PLAY_PROGRESS, percent);
            } else if (percent == 99) {
                sendMessage(WHAT_PLAY_PROGRESS, 100);
            }
        }
    };

    private void sendMessage(int what, int arg) {
        Message msg = Message.obtain(processHandler);
        msg.what = what;
        msg.arg1 = arg;
        msg.sendToTarget();
    }

    public View obtainView(Context context) {
        processHandler = new Handler(context.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case WHAT_PLAY_PROGRESS:
                        musicProgress.setProgress(musicProgress.getMax() * msg.arg1 / 100);
                        if (msg.arg1 != 100) {
                            postDelayed(runnable, 500);
                        }
                        break;
                    case WHAT_BUFF_PROGRESS:
                        musicProgress.setSecondaryProgress(musicProgress.getMax() * msg.arg1 / 100);
                        break;
                }
            }
        };
        musicLayout = View.inflate(context, R.layout.music_layout, null);
        musicName = (TextView) musicLayout.findViewById(R.id.music_name);
        musicSinger = (TextView) musicLayout.findViewById(R.id.music_singer);
        musicProgress = (SeekBar) musicLayout.findViewById(R.id.music_progress);
        updateProgress = (ProgressBar) musicLayout.findViewById(R.id.update_progress);
        playButton = (ImageView) musicLayout.findViewById(R.id.play_button);
        previousButton = (ImageView) musicLayout.findViewById(R.id.previous_button);
        nextButton = (ImageView) musicLayout.findViewById(R.id.next_button);

        musicName.setText("music name");
        musicSinger.setText("music singer");

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "playButton onClick");
                resumeOrPause();
            }
        });
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playPrevious();
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playNext();
            }
        });
        musicProgress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mediaPlayer.seekTo(mediaPlayer.getDuration() * progress / 100);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                processHandler.removeCallbacks(runnable);
                Log.i(TAG, "onStartTrackingTouch");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                resume();
                Log.i(TAG, "onStopTrackingTouch");
            }
        });
        return musicLayout;
    }
}