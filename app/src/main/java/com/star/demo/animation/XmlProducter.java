package com.star.demo.animation;

import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 多层xml生成器
 * 
 * @author jimxjiao
 * @version [version, 2014-11-5]
 * @see [relevant class/method]
 * @since [product/module version]
 */
public class XmlProducter
{
    public synchronized static String produceXml(String requestTag,
            Map<String, Object> dataMap)
    {
        StringWriter stringWriter = new StringWriter();
        try
        {
            // 获取XmlSerializer对象  
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlSerializer xmlSerializer = factory.newSerializer();
            // 设置输出流对象  
            xmlSerializer.setOutput(stringWriter);
            xmlSerializer.startDocument("GBK", true);
            
            xmlSerializer.startTag(null, requestTag);
            productTag(xmlSerializer, dataMap);
            xmlSerializer.endTag(null, requestTag);
            
            xmlSerializer.endDocument();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        return stringWriter.toString();
    }
    
    private static void productTag(XmlSerializer xmlSerializer,
            Map<String, Object> dataMap) throws Exception
    {
        for (String key : dataMap.keySet())
        {
            Object temp = dataMap.get(key);
            
            xmlSerializer.startTag(null, key);
            if (temp instanceof String)
            {
                xmlSerializer.text((String) temp);
            }
            else if (temp instanceof Map<?, ?>)
            {
                @SuppressWarnings("unchecked")
                Map<String, Object> data = (HashMap<String, Object>) temp;
                productTag(xmlSerializer, data);
            }
            /************ TODO 处理并列标签 ****************/
            else if (temp instanceof List<?>)
            {
                @SuppressWarnings("unchecked")
                List<Map<String, Object>> list = (List<Map<String, Object>>) temp;
                for (int i = 0; i < list.size(); i++)
                {
                    productTag(xmlSerializer, list.get(i));
                }
            }
            /****************************/
            else
            {
                throw new Exception(
                        "the value of the map is only to be String or Map<?,?>");
            }
            xmlSerializer.endTag(null, key);
        }
    }
}
