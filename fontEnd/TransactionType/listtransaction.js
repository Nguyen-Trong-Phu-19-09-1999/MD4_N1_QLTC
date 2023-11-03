function showAllTransaction(){
    axios.get('http://localhost:8080/api/transactionType').then((res) => {
        let str ="";
        let data = res.data;
        console.log(data)
        for (let i = 0; i < data.length; i++) {
            str += `<div>${data[i].name}
 <button onclick="deleteTran(${data[i].id})">Delete</button>
  <button onclick="showFormUpdateTran(${data[i].id})">Update</button>
  </div>`

        }
        document.getElementById('body').innerHTML = str;
    })
}
function deleteTran(id){
    axios.delete("http://localhost:8080/api/transactionType" + id).then(res => {

        showAllTransaction();
    });
}