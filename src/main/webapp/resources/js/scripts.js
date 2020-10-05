$( document ).ready(function() {

    $('body').on('click', '.profile-like', function () {
        var userId = $(this).attr('data-id');
        var self = $(this);
        console.log('like');
        $.ajax({
            type: 'GET',
            url: '/socialnetwork/users/like/' + userId,
            success: function(data) {
                if (data && data.message === 'success') {
                    var newText = data.data.status === 'Created' ? 'Unlike' : 'Like';
                }
                console.log(data);
                $(self).text(newText);
            },
        });
    }).on('click', '.profile-friend', function () {
        var userId = $(this).attr('data-id');
        var self = $(this);
        console.log('friends');
        $.ajax({
            type: 'GET',
            url: '/socialnetwork/users/friend/' + userId,
            success: function(data) {
                if (data && data.message === 'success') {
                    var newText = data.data.status === 'Created' ? 'Delete from friends' : 'Add to friends';
                }
                console.log(data);
                $(self).text(newText);
            },
        });
    });
});