/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$('.form .stages label').click(function() {
    var radioButtons = $('.form input:radio');
    var selectedIndex = radioButtons.index(radioButtons.filter(':checked'));
    selectedIndex = selectedIndex + 1;
});

$('.form button').click(function() {
    var radioButtons = $('.form input:radio');
    var selectedIndex = radioButtons.index(radioButtons.filter(':checked'));

    selectedIndex = selectedIndex + 2;

    $('.form input[type="radio"]:nth-of-type(' + selectedIndex + ')').prop('checked', true);

    if (selectedIndex == 6) {
            $('button').html('Submit');
    }
});
