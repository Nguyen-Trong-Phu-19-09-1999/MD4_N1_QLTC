let str ='';
function loadUser(){
    let name = localStorage.getItem('name');
    if(name ==null){
        document.getElementById('loaduser').innerHTML =''

    }else {
        document.getElementById('loaduser').innerHTML= `<div>
          xin chao ${name}
          <div onclick="logout()">Logout</div>
          </div>`

    }
}
function logout(){
    localStorage.removeItem('name');
    loadUser();
}
