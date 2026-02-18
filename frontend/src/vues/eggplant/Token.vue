<script setup>
import { ref } from 'vue';

let generatedToken = ref("");

const generateToken = async (event) => {
  event.preventDefault();
  let location = document.getElementById('locationInput').value;
  let department = document.getElementById('departmentInput').value;
  let title = document.getElementById('titleInput').value;

  const tokenData = {
    location,
    department,
    title,
  };

  try {
    // TODO: Replace with actual API call to generate token
    // let response = await generateTokenAPI(tokenData);
    // if (response.data) {
    //   generatedToken.value = response.data.token;
    // }
    
    // Placeholder token generation
    generatedToken.value = `TOKEN_${location}_${department}_${title}_${Date.now()}`;
  } catch (error) {
    generatedToken.value = "Error generating token. Please try again.";
  }
}

</script>

<template>
    <div className="register">
      <h2>Generate a Token</h2>
      <form>
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

        <button @click="generateToken" type="button">Generate Token</button>

        <div v-if="generatedToken" className="tokenBox">
          <textarea id="generatedToken" readonly>{{ generatedToken }}</textarea>
        </div>
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
form {
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 1rem;
  overflow-y: auto;
}
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
button {
  margin-top: 1rem;
  padding: 0.75rem 2rem;
  background-color: var(--purplelite);
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-weight: bold;
}
.tokenBox {
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 1rem;
  margin-top: 0.5rem;
}
.tokenBox textarea {
  width: 90%;
  padding: 0.5rem;
  border-radius: 5px;
  border: none;
  background-color: var(--purplelite);
  font-family: monospace;
  height: 100px;
  resize: vertical;
}
</style>