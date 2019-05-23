<#include "../base.ftl">
<#macro page_head>
    <title>Article</title>
</#macro>

<#macro page_body>
    <h1 class="my-lg-5 text-center">${title}</h1>
    <p class="text-center">${text}</p>

    <ul>
        <#list comments as comment>
            <li class="my-lg-2 px-5">
                <div class="card">
                    <div class="card-body">
                        <p class="card-text">${comment.content}</p>
                        <a href="/admin/comment/del/${comment.id}/${id}" class="btn btn-danger">Delete</a>
                    </div>
                </div>
            </li>
        </#list>
    </ul>
</#macro>

<@display_page/>