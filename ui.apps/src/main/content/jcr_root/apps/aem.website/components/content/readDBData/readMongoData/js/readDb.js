$(document).ready(function(){
    alert("duh");
    var html = "";
      $.get("/bin/readFormData",function(data){
          var element = data.result;
          console.log("element",element);
          for (var i=0;i<element.length;i++){
          html += `<div class="card">
    <div class="card-header" id="heading${i}">
      <h5 class="mb-0">
          <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapse${i}" aria-expanded="false" aria-controls="collapse${i}">
              ${element[i].firstname}
        </button>
      </h5>
    </div>
    <div id="collapse${i}" class="collapse" aria-labelledby="heading${i}" data-parent="#accordion">
      <div class="card-body">
			${element[i].firstname}
      </div>
    </div>
  </div>`
          }

          $("#accordion").append(html);
      });

});