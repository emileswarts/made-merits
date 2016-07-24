$( document ).ready(function() {
  $('.leader-board__user').on('click', function(e) {
    $target = e.currentTarget;

    $($target).next('.leader-board__user-merits').toggle();
  });
});
