$(document).ready(function() {
    var rowsPerPage = 17;
    var rows = $('#pointList tr');
    var rowsCount = rows.length;
    var pageCount = Math.ceil(rowsCount / rowsPerPage);
    var numbers = $('#pagination');

    // Generate the pagination
    for (var i = 1; i <= pageCount; i++) {
        numbers.append('<a href="#">' + i + '</a>');
    }

    // Mark the first page link as active
    $('#pagination a:first').addClass('active');

    // Display the first set of rows
    displayRows(1);

    // On clicking the pagination links
    $('#pagination a').click(function(e) {
        e.preventDefault();
        $('#pagination a').removeClass('active');
        $(this).addClass('active');
        var page = $(this).text();
        displayRows(page);
    });

    function displayRows(index) {
        var start = (index - 1) * rowsPerPage;
        var end = start + rowsPerPage;
        rows.hide();
        rows.slice(start, end).show();
    }
});
