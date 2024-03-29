<#include "header.ftl">


<div id="main">
    <div class="container" id="daily">
        <div class="jscroll-inner">
            <div class="daily">

                <#assign  cur_date =''/>
                <#list vos as vo >
                <#if cur_date != vo.news.createdDate?string("yyyy-MM-dd")>
                <#if vo_index gt 0 >
            </div> <#--   上一个要收尾 -->
            </#if>
            <#assign  cur_date =vo.news.createdDate?string("yyyy-MM-dd")/>
            <h3 class="date">
                <i class="fa icon-calendar"></i>
                <span>IT资讯 &nbsp; ${vo.news.createdDate?string("yyyy-MM-dd")}</span>
            </h3>
            <div class="posts">
                </#if>
                <div class="post">
                    <div class="votebar">
                        <#if  vo.likeCount gt 0 >
                            <button class="click-like up pressed" data-id="${vo.news.id!}" title="赞同"><i class="vote-arrow"></i><span class="count">${vo.news.likeCount!}</span></button>
                        <#else>
                            <button class="click-like up" data-id="${vo.news.id!}" title="赞同"><i class="vote-arrow"></i><span class="count">${vo.news.likeCount!}</span></button>
                        </#if>
                        <#if vo.likeCount < 0>
                            <button class="click-dislike down pressed" data-id="${vo.news.id!}" title="反对"><i class="vote-arrow"></i></button>
                        <#else>
                            <button class="click-dislike down" data-id="${vo.news.id!}" title="反对"><i class="vote-arrow"></i></button>
                        </#if>
                    </div>
                    <div class="content">
                        <div >
                            <img class="content-img" src="${vo.news.image!}" alt="">
                        </div>
                        <div class="content-main">
                            <h3 class="title">
                                <a target="_blank" rel="external nofollow" href="${springMacroRequestContext.contextPath}/news/${vo.news.id!}">${vo.news.title!}</a>
                            </h3>
                            <div class="meta">
                                ${vo.news.link!}
                                <span>
                                            <i class="fa icon-comment"></i> ${vo.news.commentCount!}
                                        </span>
                            </div>
                        </div>
                    </div>
                    <div class="user-info">
                        <div class="user-avatar">
                            <#if vo.user ??>


                            <a href="${springMacroRequestContext.contextPath!}/user/${vo.user.id}/"><img width="32" class="img-circle" src="${vo.user.headUrl}"></a>
                            </#if>
                        </div>


                    </div>

                    <div class="subject-name">来自 <#if vo.user ??><a href="${springMacroRequestContext.contextPath!}/user/${vo.user.id}/">${vo.user.name}</a></#if></div>
                </div>


                <#if vo_index == vos?size >
            </div>  <#--最后有个元素要收尾 -->
            </#if>

            </#list>


        </div>
    </div>
</div>

</div>


<#if pop??>
    <script>
        window.loginpop = ${(pop==0)?c};
    </script>
</#if>
<#include "footer.ftl">