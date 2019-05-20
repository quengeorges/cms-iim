<#include "base.ftl">
<#macro page_head>
    <title>Article</title>
</#macro>

<#macro page_body>
    <h1>${title}</h1>
    <p>${text}</p>
</#macro>

<@display_page/>