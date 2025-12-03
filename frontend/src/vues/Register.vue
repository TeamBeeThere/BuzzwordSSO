<script setup></script>

<template>
    <div className="register">
      <form>
       <div><label for="firstName">First Name</label>
       <input type="text" id="firstNameInput" name="firstName"/>
       </div>
       <div><label for="lastName">Last Name</label>
       <input type="text" id="lastNameInput" name="lastName"/>
       </div>
       <div><label for="location">Location</label>
       <select id="locationInput" name="location">
          <option disabled>Please Select The Location</option>
          <option value="Japan">Japan</option>
          <option value="Brazil">Brazil</option>
          <option value="United States">United States</option>
          <option value="South Africa">South Africa</option>
          <option value="Germany">Germany</option>
        </select>
        </div>

       <div><label for="Department">Department</label>
       <select id="departmentInput" name="department">
          <option disabled>Please Select The Department</option>
          <option value="Sales">Sales</option>
          <option value="Information Technology">Information Technology</option>
          <option value="Legal">Legal</option>
          <option value="Hr">Hr</option>
        </select>
        </div>

      <div><label for="Title">Title</label>
       <select id="titleInput" name="title">
          <option disabled >Please Select The Title</option>
          <option value="Aide">Aide</option>
          <option value="Developer">Developer</option>
          <option value="Sales Agent">Sales Agent</option>
          <option value="Manager">Manager</option>
        </select>
        </div>
        <button text="Register Employee">Register </button>  
       </form>
    
    </div>
</template>

<style scoped>
.register {
    height: 60%;
    width: 100%;
  background-color: var(--green);
  text-align: center;
  display: flex;
  align-items: center;
  }
form {
  width: 100%;
  display: flex; 
  flex-direction: column;
  align-items: center;
  gap: 1rem;}
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
}
</style>