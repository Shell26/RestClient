
function createTable() {

    let html = $('<tbody/>');
    let addCell = function ($tr, content) {
        let $td = $('<td/>');
        $td.append(content);
        $tr.append($td);
    };

    $.getJSON('/rest/users', function (users) {  //почему не получается изменить?

        users.forEach(function (user) {
            let $tr = $('<tr/>');

            addCell($tr, user.id);
            addCell($tr, user.name);
            addCell($tr, user.login);
            addCell($tr, user.password);
            addCell($tr, user.roles[0].role);
            addCell($tr,
                `<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal-${user.id}">Edit</button> 
                            <div class="modal fade" id="exampleModal-${user.id}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="exampleModalLabel">Edit User</h5>
                                            <button type="button" class="close" data-dismiss="modal"
                                                    aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                            <form action="/rest/admin/edit/${user.id}" method="POST" onsubmit="return editUser(${user.id}, this)">
                                            <div class="modal-body">

                                                <strong>Username</strong>
                                                <input class="d-flex justify-content-center form-control form-control-lg"
                                                       type="text" value="${user.name}"
                                                       placeholder="name" name="name" required>
                                                <strong>Login</strong>
                                                <input class="d-flex justify-content-center form-control form-control-lg"
                                                       type="text"  value="${user.login}"
                                                       placeholder="login" name="login" required>
                                                <strong>Password</strong>
                                                <input class="d-flex justify-content-center form-control form-control-lg"
                                                       type="text"
                                                       placeholder="password" name="password">
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-secondary"
                                                            data-dismiss="modal">Close
                                                    </button>
                                                    <button type="submit" value="Edit" class="btn btn-primary">Edit</button>
                                                </div>
                                            </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>`)
            addCell($tr, `<button onclick="return deleteUser(${user.id})" id="delbut" type="button" class="btn btn-primary">Delete</button>`);

            html.append($tr);
        });
        $('#tableUsers tbody').replaceWith(html);
    });
}

$(function () {
    createTable();
});

function deleteUser(id) {
    let url = 'rest/admin/delete/' + id;
    $.ajax({
        url: url,
        type: "delete",
        contentType: 'application/json',
        success: function () {
            location.reload();
            createTable();
        },
        error: function () {
            alert("Не удалось удалить данные");
        }
    });
    return false;
}

function editUser(id, form) {
    let url = '/rest/admin/edit/' + id;
    let $form = $(form);
    $.ajax({
        url: url,
        type: "post",
        data: $form.serializeArray(),
        success: function () {
            $('.modal-backdrop').remove();
            createTable();
        },
        error: function () {
            alert("Не удалось edit данные");
        }
    });
    return false;
}

function saveUser(form) {
    let $form = $(form);
    $.ajax({
        url: "/rest/admin/save/",
        type: "post",
        data: $form.serializeArray(),
        success: function () {
            location.reload();
            createTable();
        },
        error: function () {
            alert("Не удалось отправить данные для редактирования");
        }
    });
    return false;
}
