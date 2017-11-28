package cn.ltl.crawler;

import java.io.File;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import cn.ltl.crawler.service.HttpService;
import cn.ltl.crawler.service.PropertieService;

/**
 * 图片下载
 * 
 * @author ltl
 *
 * @date 2017年11月10日 下午4:30:21
 *
 * @version V1.0
 */
public class ImageDownloadCrawler implements Crawler {

    /**
     * key：原始图片地址 value:新图片地址
     */
    private Map<String, String> urlMapping;

    public ImageDownloadCrawler(Map<String, String> urlMapping) {
        this.urlMapping = urlMapping;
    }

    @Override
    public void run() {
        if (this.urlMapping == null || this.urlMapping.isEmpty()) {
            return;
        }
        HttpService httpService = Main.applicationContext.getBean(HttpService.class);
        PropertieService propertieService = Main.applicationContext.getBean(PropertieService.class);
        for (Map.Entry<String, String> entry : this.urlMapping.entrySet()) {
            try {
                String url = entry.getKey();
                if (!StringUtils.startsWith(url, "http://")) {
                    url = "http:" + url;

                }
                httpService.downloadFile(url, new File(propertieService.IMAGE_DIR + entry.getValue()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
