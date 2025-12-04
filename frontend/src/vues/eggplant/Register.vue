<script setup>
import { ref } from 'vue';
import { addEmployee } from '../../utils/services.js';

let registerResponse = ref("");

const registerEmployee = async (event) => {
  let firstName = document.getElementById('firstNameInput').value;
  let lastName = document.getElementById('lastNameInput').value;
  let username = firstName.charAt(0).toLowerCase() + lastName.toLowerCase();
  let password = "eggplant123!";

  event.preventDefault();
    let newEmployee = {
      firstName,
      lastName,
      username,
      password,
      location: document.getElementById('locationInput').value,
      department: document.getElementById('departmentInput').value,
      title: document.getElementById('titleInput').value,
    };
    let response = await addEmployee(newEmployee);
    if (response.data) {
      registerResponse.value = `${newEmployee.firstName} ${newEmployee.lastName} registered successfully!`;
    }
    else {
      registerResponse.value = `Error registering Employee. Please try again. Or contact support.`;
    }
}

</script>

<template>
    <div className="register">
      <h2>Register Employee</h2>
      <form>
       <div><label for="firstName">First Name</label>
       <input type="text" id="firstNameInput" name="firstName"/>
       </div>
       <div><label for="lastName">Last Name</label>
       <input type="text" id="lastNameInput" name="lastName"/>
       </div>
       <div><label for="location">Location</label>
       <select id="locationInput" name="location">
          <option disabled selected>Select</option>
          <option value="Japan">Japan</option>
          <option value="Brazil">Brazil</option>
          <option value="United States">United States</option>
          <option value="South Africa">South Africa</option>
          <option value="Germany">Germany</option>
        </select>
        </div>

       <div><label for="Department">Department</label>
       <select id="departmentInput" name="department">
          <option disabled selected>Select</option>
          <option value="Sales">Sales</option>
          <option value="Information Technology">Information Technology</option>
          <option value="Legal">Legal</option>
          <option value="Hr">Hr</option>
        </select>
        </div>

      <div><label for="Title">Title</label>
       <select id="titleInput" name="title">
          <option disabled selected>Select</option>
          <option value="Aide">Aide</option>
          <option value="Developer">Developer</option>
          <option value="Sales Agent">Sales Agent</option>
          <option value="Manager">Manager</option>
        </select>
        </div>
        <p className="errorMessage">{{ registerResponse }}</p>
        <button @click="registerEmployee" text="Register Employee">Register </button>  
       </form>
    
    </div>
</template>

<style scoped>
.register {
  text-align: center;
  display: flex;
  flex-direction: column;
  align-items: center;
  }
.errorMessage {
  color: orange;
}
form {
  width: 100%;
  display: flex; 
  flex-direction: column;
  align-items: center;
  gap: 1rem;
 overflow-y: auto;}
form div {
  width: 100%;
  display: flex;
  flex-direction: row;
  text-align: center;
  justify-content: center; 
  gap: 2rem;
}
form label {
  width: 40%;
  text-align: right;
}
form input, form select {
  width: 50%;
  padding: 0.5rem;
  border-radius: 5px;
  border: none;
  margin-right: 1rem;
  background-color: var(--purplelite);
}
</style>