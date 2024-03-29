<#include "header.ftl">
    <div id="main">
        <div class="container">
            <ul class="letter-list">
                <#list  conversations as conversation >
                <li id="conversation-item-10005_622873">
                    <a class="letter-link" href="${springMacroRequestContext.contextPath}/msg/detail?conversationId=${conversation.conversation.conversationId!}"></a>
                    <div class="letter-info">
                        <span class="l-time">${conversation.conversation.createdDate?string('yyyy-MM-dd HH:mm:ss')}</span>
                        <div class="l-operate-bar">
                            <a href="${springMacroRequestContext.contextPath}/msg/deleteMessages?conversationId=${conversation.conversationId}" class="sns-action-del" data-id="10005_622873">
                            删除
                            </a>
                            <a href="${springMacroRequestContext.contextPath}/msg/detail?conversationId=${conversation.conversation.conversationId!}">
                                共${conversation.count!}条会话
                            </a>
                        </div>
                    </div>
                    <div class="chat-headbox">
                        <span class="msg-num">
                            ${conversation.count!}
                        </span>
                        <a class="list-head" href="${springMacroRequestContext.contextPath}/user/${conversation.user.id!}">
                            <img alt="头像" src="${conversation.user.headUrl!}">
                        </a>
                    </div>
                    <div class="letter-detail">
                        <a title="${conversation.user.name!}" class="letter-name level-color-1">
                            ${conversation.user.name!}
                        </a>
                        <p class="letter-brief">
                            <a href="${springMacroRequestContext.contextPath}/msg/detail?conversationId=${conversation.conversation.conversationId!}">
                                ${conversation.conversation.content!}
                            </a>
                        </p>
                    </div>
                </li>
                </#list>
            </ul>

        </div>
        <script type="text/javascript">
          $(function(){

            // If really is weixin
            $(document).on('WeixinJSBridgeReady', function() {

              $('.weixin-qrcode-dropdown').show();

              var options = {
                "img_url": "",
                "link": "http://cskaoyan.com/j/wt2rwy",
                "desc": "",
                "title": "读《Web 全栈工程师的自我修养》"
              };

              WeixinJSBridge.on('menu:share:appmessage', function (argv){
                WeixinJSBridge.invoke('sendAppMessage', options, function (res) {
                  // _report('send_msg', res.err_msg)
                });
              });

              WeixinJSBridge.on('menu:share:timeline', function (argv) {
                WeixinJSBridge.invoke('shareTimeline', options, function (res) {
                  // _report('send_msg', res.err_msg)
                });
              });

              // $(window).on('touchmove scroll', function() {
              //   if ((window.innerHeight + window.scrollY) >= document.body.offsetHeight) {
              //     $('div.backdrop').show();
              //     $('div.share-help').show();
              //   } else {
              //     $('div.backdrop').hide();
              //     $('div.share-help').hide();
              //   }
              // });

            });

          })
        </script>
    </div>
<#include "footer.ftl">
