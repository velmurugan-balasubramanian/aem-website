$(document).ready(function(){



  $("#formSubmit").click(function(){
    const firstname = $('#firstname').val();
    const lastname = $('#lastname').val();
    const email = $('#email').val();
    const enquiry = $('#enquiry').val();
      let userDetails = {"firstname":firstname,"lastname":lastname,"email":email,"enquiry":enquiry}
      console.log("firstname",firstname);
     console.log("abcde",userDetails);
      $.get("/libs/granite/csrf/token.json",function(data){
             saveValue(data.token,userDetails);
      });

  });
    function saveValue(token,userDetails){

        $.ajax({
            url: "/bin/saveFormData",
            type: "post",
            data: userDetails,
            datatype: 'json',
            beforeSend: function (xhr) {
                alert(token);
                xhr.setRequestHeader("CSRF-Token", token);
            }
        }).done(function(data){
			console.log("data",data);
        }).fail(function(data){

        });


    }
});