package com.example.tctctc.easylook.ganhuojizhongying.Model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tctctc on 2016/9/24.
 */

public class CateGanHuo {

    /**
     * error : false
     * results : [{"_id":"57dfafae421aa95bc7f06a60","createdAt":"2016-09-19T17:28:14.261Z","desc":"任阅小说阅读器，高仿追书神器，实现追书推荐、标签检索、3D仿真翻页效果、文章阅读、缓存章节、日夜间模式、文本朗读等功能。","publishedAt":"2016-09-23T11:38:57.170Z","source":"web","type":"Android","url":"https://github.com/JustWayward/BookReader","used":true,"who":"LeBron_Six"},{"_id":"57e4750d421aa95bc338987b","createdAt":"2016-09-23T08:19:25.501Z","desc":"RxJava + 权限申请","publishedAt":"2016-09-23T11:38:57.170Z","source":"chrome","type":"Android","url":"https://github.com/VictorAlbertos/RxPermissionsResult","used":true,"who":"代码家"},{"_id":"57e476eb421aa95bcb13017d","createdAt":"2016-09-23T08:27:23.443Z","desc":"Material Design 风格的投票效果！","publishedAt":"2016-09-23T11:38:57.170Z","source":"chrome","type":"Android","url":"https://github.com/hiteshsahu/Material-UpVote","used":true,"who":"代码家"},{"_id":"57e477e9421aa95bd0501615","createdAt":"2016-09-23T08:31:37.367Z","desc":"轮盘样式的 Fragment 菜单，可转动轮盘切换 Fragment","publishedAt":"2016-09-23T11:38:57.170Z","source":"web","type":"Android","url":"https://github.com/Hitomis/SpinMenu","used":true,"who":"Hitomi"},{"_id":"56e7a52867765931185ddbc9","createdAt":"2016-03-15T14:01:12.743Z","desc":"一个实现树形内容展示的库","publishedAt":"2016-09-22T11:44:08.156Z","source":"chrome","type":"Android","url":"https://github.com/Telenav/NodeFlow","used":true,"who":"Dear宅学长"},{"_id":"57e2ac20421aa95bd05015ff","createdAt":"2016-09-21T23:49:52.47Z","desc":"抽取自Telegram，并加入QQ相册选择风格的图片选择器，高效，低耗，响应快速。。","publishedAt":"2016-09-22T11:44:08.156Z","source":"web","type":"Android","url":"https://github.com/TangXiaoLv/TelegramGallery","used":true,"who":"XiaoLv Tang"},{"_id":"57e31e0c421aa95bcb13016e","createdAt":"2016-09-22T07:55:56.609Z","desc":"锯齿边框，类似优惠劵效果的自定义 View","publishedAt":"2016-09-22T11:44:08.156Z","source":"chrome","type":"Android","url":"https://github.com/dongjunkun/CouponView","used":true,"who":"代码家"},{"_id":"57e31e65421aa95bd0501606","createdAt":"2016-09-22T07:57:25.844Z","desc":"简洁的图片裁剪小裤子","publishedAt":"2016-09-22T11:44:08.156Z","source":"chrome","type":"Android","url":"https://github.com/ekimual/croperino","used":true,"who":"代码家"},{"_id":"57e34409421aa95bc7f06a88","createdAt":"2016-09-22T10:38:01.371Z","desc":" 自律给你自由\u2014\u2014设计布局的新姿势，讲解ConstraintLayout的使用方式","publishedAt":"2016-09-22T11:44:08.156Z","source":"web","type":"Android","url":"http://blog.csdn.net/eclipsexys/article/details/52609367","used":true,"who":"xuyisheng"},{"_id":"57dfc6a3421aa95bc338983e","createdAt":"2016-09-19T19:06:11.394Z","desc":"App瘦身最佳实践","publishedAt":"2016-09-21T11:37:24.210Z","source":"chrome","type":"Android","url":"http://www.jianshu.com/p/8f14679809b3","used":true,"who":"MVP"}]
     */

    private boolean error;
    /**
     * _id : 57dfafae421aa95bc7f06a60
     * createdAt : 2016-09-19T17:28:14.261Z
     * desc : 任阅小说阅读器，高仿追书神器，实现追书推荐、标签检索、3D仿真翻页效果、文章阅读、缓存章节、日夜间模式、文本朗读等功能。
     * publishedAt : 2016-09-23T11:38:57.170Z
     * source : web
     * type : Android
     * url : https://github.com/JustWayward/BookReader
     * used : true
     * who : LeBron_Six
     */

    private List<CateBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<CateBean> getResults() {
        return results;
    }

    public void setResults(List<CateBean> results) {
        this.results = results;
    }

    public static class CateBean implements Serializable{
        private String _id;
        private String createdAt;
        private String desc;
        private List<String> images;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;

        @Override
        public String toString() {
            return "CateBean{" +
                    "_id='" + _id + '\'' +
                    ", createdAt='" + createdAt + '\'' +
                    ", desc='" + desc + '\'' +
                    ", publishedAt='" + publishedAt + '\'' +
                    ", source='" + source + '\'' +
                    ", type='" + type + '\'' +
                    ", url='" + url + '\'' +
                    ", used=" + used +
                    ", who='" + who + '\'' +
                    '}';
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }
    }

    @Override
    public String toString() {
        return "CateGanHuo{" +
                "error=" + error +
                ", results=" + results +
                '}';
    }
}
