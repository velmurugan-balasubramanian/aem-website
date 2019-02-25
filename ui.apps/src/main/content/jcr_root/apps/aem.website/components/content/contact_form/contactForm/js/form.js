$(document).ready(function(){

  $("#formSubmit").click(function(){
      alert("workin");
   /* $.get("demo_test.asp", function(data, status){
      alert("Data: " + data + "\nStatus: " + status);
    }); */
      $.post( "/bin/saveFormData",
       {
           name:"vel"
      }, function( data ) {
  alert(data);
});
  });
});