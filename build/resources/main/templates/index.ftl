<#include "base.ftl">
<#macro page_head>
    <title>List Article</title>
</#macro>

<#macro page_body>
    <#list list as article>
        <a href="/article/${article.id}">${article.title}</a>
    </#list>
</#macro>

<@display_page/>