$(document).ready(function(){
    $(".cartItemQty").on('input', function(){
        var id=this.id;

        $('#update-item-'+id).css('display', 'inline-block');
    });
});