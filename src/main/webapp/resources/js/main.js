function update(student_id) {
    fetch('http://10.10.4.29:8080/students/get/' + student_id)
        .then(response => response.json())
        .then(json => {
            document.getElementById("u_id").value = json.id;
            document.getElementById("u_firstName").value = json.firstName;
            document.getElementById("u_lastName").value = json.lastName;
            document.getElementById("u_age").value = json.age;
        });

}