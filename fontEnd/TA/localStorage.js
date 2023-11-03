function loadUser(){
    let name = localStorage.getItem('name');
    if(name ==null){
        document.getElementById('UserName').innerHTML =``

    }else {
        document.getElementById('UserName').innerHTML= `<div>
          xin chao ${name}
          <div onclick="logout()">Logout</div>
          </div>`

    }
}
function logout(){
    localStorage.removeItem('name');
    loadUser();
}