/**
 * Created by geovani.anoman on 12/5/18.
 */
$(document).ready(function() {
    changePageAndSize();
});

function changePageAndSize() {
    $('#pageSizeSelect').change(function(evt) {
        window.location.replace("/?size=" + this.value + "&page=1");
    });
}