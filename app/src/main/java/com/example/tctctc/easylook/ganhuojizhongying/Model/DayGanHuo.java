package com.example.tctctc.easylook.ganhuojizhongying.Model;

import java.util.List;

/**
 * Created by tctctc on 2016/9/23.
 */

public class DayGanHuo {

    /**
     * category : ["Android","iOS","前端","福利","拓展资源","休息视频"]
     * error : false
     * results : {"Android":[{"_id":"56cc6d1d421aa95caa70785b","createdAt":"2015-09-07T09:16:59.602Z","desc":"一个漫画app","publishedAt":"2015-09-22T03:53:01.563Z","type":"Android","url":"https://github.com/android-cjj/ComicReader","used":true,"who":"有时放纵"},{"_id":"56cc6d1d421aa95caa7078d0","createdAt":"2015-09-22T02:48:56.876Z","desc":"React-native-android 系列中文教材","publishedAt":"2015-09-22T03:53:01.581Z","type":"Android","url":"https://github.com/yipengmu/react-native-android-lession","used":true,"who":"Dear宅学长"},{"_id":"56cc6d26421aa95caa707cfc","createdAt":"2015-09-13T05:35:52.299Z","desc":"大图作为header跟随手指向上滑动，下拉展示大图","publishedAt":"2015-09-22T03:53:01.566Z","type":"Android","url":"https://github.com/w4lle/PullDownView","used":true,"who":"Jason"},{"_id":"56cc6d26421aa95caa707cfd","createdAt":"2015-09-13T05:48:37.348Z","desc":"一个通过动态加载本地皮肤包进行换肤的皮肤框架","publishedAt":"2015-09-22T03:53:01.568Z","type":"Android","url":"https://github.com/fengjundev/Android-Skin-Loader","used":true,"who":"Jason"},{"_id":"56cc6d26421aa95caa707d05","createdAt":"2015-09-14T06:58:10.212Z","desc":"一个可以任意移动的floating View，当你的app在后台运行也可以显示。","publishedAt":"2015-09-22T03:53:01.569Z","type":"Android","url":"http://jcmore2.github.io/FreeView/","used":true,"who":"06peng"},{"_id":"56cc6d26421aa95caa707d3e","createdAt":"2015-09-21T09:00:13.965Z","desc":"左滑删除粒子效果","publishedAt":"2015-09-22T03:53:01.579Z","type":"Android","url":"https://github.com/android-cjj/Android-MaterialDeleteLayout","used":true,"who":"Jason"}],"iOS":[{"_id":"56cc6d1d421aa95caa7078b0","createdAt":"2015-09-15T15:28:12.161Z","desc":"如何打造可扩展组件","publishedAt":"2015-09-22T03:53:01.570Z","type":"iOS","url":"http://christiantietze.de/posts/2015/09/two-service-types/","used":true,"who":"CallMeWhy"},{"_id":"56cc6d1d421aa95caa7078cd","createdAt":"2015-09-21T14:23:11.519Z","desc":"Swift 开发者周刊（第 0 期）","publishedAt":"2015-09-22T03:53:01.580Z","type":"iOS","url":"http://doswift.io/archive/0.html","used":true,"who":"Andrew Liu"},{"_id":"56cc6d1d421aa95caa7078d1","createdAt":"2015-09-22T02:51:34.687Z","desc":"OC转换成Swift的小工具","publishedAt":"2015-09-22T03:53:01.582Z","type":"iOS","url":"http://iswift.org/?utm_source=next.36kr.com","used":true,"who":"Dear学长"},{"_id":"56cc6d26421aa95caa707d20","createdAt":"2015-09-16T04:50:48.647Z","desc":"系列: swift实现一个与智能机器人聊天的app","publishedAt":"2015-09-22T03:53:01.572Z","type":"iOS","url":"http://www.jianshu.com/p/a09ceaebe797?utm_campaign=maleskine&utm_content=note&utm_medium=writer_share&utm_source=weibo","used":true,"who":"Andrew Liu"},{"_id":"56cc6d26421aa95caa707d21","createdAt":"2015-09-16T04:56:14.835Z","desc":"系列: 为 Apple TV 开发 tvOS App","publishedAt":"2015-09-22T03:53:01.574Z","type":"iOS","url":"http://swift.gg/2015/09/14/developing-tvos-apps-for-apple-tv-with-swift/","used":true,"who":"Andrew Liu"}],"休息视频":[{"_id":"56cc6d26421aa95caa707d36","createdAt":"2015-09-18T14:11:32.523Z","desc":"大学课堂老师教欢乐斗地主，这课满意度我给满分","publishedAt":"2015-09-22T03:53:01.577Z","type":"休息视频","url":"http://video.weibo.com/show?fid=1034:f0df6ad948744a9df2e14333280fe759&ep=CB7T0uxbf%2C1713926427%2CCB7T0uxbf%2C1713926427","used":true,"who":"lxxself"}],"前端":[{"_id":"56cc6d1d421aa95caa7078cf","createdAt":"2015-09-22T01:57:03.310Z","desc":"前端开发规范手册","publishedAt":"2015-09-22T03:53:01.580Z","type":"前端","url":"https://github.com/Aaaaaashu/Front-End-Style-Guide","used":true,"who":"鲍永章"}],"拓展资源":[{"_id":"56cc6d26421aa95caa707d35","createdAt":"2015-09-18T08:23:59.885Z","desc":"在线学习git操作：Learn Git Branching!","publishedAt":"2015-09-22T03:53:01.576Z","type":"拓展资源","url":"http://pcottle.github.io/learnGitBranching/","used":true,"who":"lxxself"}],"福利":[{"_id":"56cc6d1d421aa95caa7078d2","createdAt":"2015-09-22T03:43:31.996Z","desc":"9.22-可爱型！！！","publishedAt":"2015-09-22T03:53:01.583Z","type":"福利","url":"http://ww3.sinaimg.cn/large/7a8aed7bgw1ewb2ytx5okj20go0p0jva.jpg","used":true,"who":"张涵宇"}]}
     */

    private boolean error;
    private List<CateGanHuo.CateBean> results;
    private List<String> category;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<CateGanHuo.CateBean> getResults() {
        return results;
    }

    public void setResults(List<CateGanHuo.CateBean> results) {
        this.results = results;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }


    @Override
    public String toString() {
        return "DayGanHuo{" +
                "error=" + error +
                ", results=" + results +
                ", category=" + category +
                '}';
    }
}
