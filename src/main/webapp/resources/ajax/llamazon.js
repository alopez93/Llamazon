function downloadBook(bookId){
      var format = $('#format').val(); 
      $.ajax({
        url: "downloadBook?format="+format+"&bookId="+bookId,
        type: "GET",
    });	
}