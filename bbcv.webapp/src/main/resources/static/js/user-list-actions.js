var $table = $('#fresh-table'),
    $createUserBtn = $('#createUser'),
    $modal = $('#modal').modal({show: false}),
    $alert = $('.alert').hide(),
    full_screen = false;

$().ready(function() {
    $table.bootstrapTable({
        showToggle: true,
        showColumns: true,
        striped: true,
        pageSize: 8,
        pageList: [8,10,25,50,100],

        formatShowingRows: function(pageFrom, pageTo, totalRows){
            //do nothing here, we don't want to show the text "showing x of y from..."
        },
        formatRecordsPerPage: function(pageNumber){
            return pageNumber + " rows visible";
        },
        icons: {
            refresh: 'fa fa-refresh',
            toggle: 'fa fa-th-list',
            columns: 'fa fa-columns',
            detailOpen: 'fa fa-plus-circle',
            detailClose: 'fa fa-minus-circle'
        }
    });

    $(window).resize(function () {
        $table.bootstrapTable('resetView');
    });

    window.operateEvents = {
        'click .edit': function (e, value, row) {
            showModal($(this).attr('title'), row);
        },
        'click .remove': function (e, value, row) {
            $table.bootstrapTable('remove', {
                field: 'id',
                values: [row.id]
            });

        }
    };

    $(function () {
        // create event
        $('.create').click(function () {
            showModal($(this).text());
        });

        $modal.find('.submit').click(function () {
            var row = {};

            $modal.find('input[name]').each(function () {
                row[$(this).attr('name')] = $(this).val();
            });

            // required by Spring Security
            var header = $("meta[name='_csrf_header']").attr("content");
            var token = $("meta[name='_csrf']").attr("content");

            $.ajax({
                url: '/admin/user/' + ($modal.data('id') || ''),
                type: $modal.data('id') ? 'put' : 'post',
                contentType: 'application/json',
                data: JSON.stringify(row),
                beforeSend: function(xhr) {
                    xhr.setRequestHeader(header, token);
                },
                success: function () {
                    $modal.modal('hide');
                    $table.bootstrapTable('refresh');
                    showAlert('The item was ' + ($modal.data('id') ? 'updated' : 'created'), 'success');
                },
                error: function () {
                    $modal.modal('hide');
                    showAlert('The item was not ' + ($modal.data('id') ? 'updated' : 'created'), 'danger');
                }
            });
        });
    });

});

function queryParams(params) {
    return {};
}

function operateFormatter(value, row, index) {
    return [
        '<a rel="tooltip" title="Edit" class="table-action edit" href="javascript:"><i class="fa fa-edit"></i></a>',
        '<a rel="tooltip" title="Remove" class="table-action remove" href="javascript:"><i class="fa fa-remove"></i></a>'
    ].join('');
}

function showModal(title, row) {
    row = row || {
        userName: '',
        firstName: '',
        lastName: '',
        email: ''
    }; // default row value

    $modal.data('id', row.id);
    $modal.find('.modal-title').text(title);
    for (var name in row) {
        $modal.find('input[name="' + name + '"]').val(row[name]);
    }
    $modal.modal('show');
}

function showAlert(title, type) {
    $alert.attr('class', 'alert alert-' + type || 'success')
          .html('<i class="glyphicon glyphicon-check"></i> ' + title).show();
    setTimeout(function () {
        $alert.hide();
    }, 3000);
}