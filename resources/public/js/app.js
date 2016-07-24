$( document ).ready(function() {
  $('.leader-board__user').on('click', function(e) {
    $target = e.currentTarget;

    $('.leader-board__user-merits').hide();
    $($target).next('.leader-board__user-merits').toggle();
  });
};
