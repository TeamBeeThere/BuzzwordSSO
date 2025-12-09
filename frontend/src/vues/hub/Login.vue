<script setup>
import { ref, inject } from 'vue';
import { useRouter } from 'vue-router';
import axios from 'axios';

const API_URL = 'http://localhost:6767/sso';
const router = useRouter();

const handleLogin = inject('handleLogin');

const username = ref('');
const password = ref('');
const errorMessage = ref('');
const isLoading = ref(false);

const submitLogin = async (event) => {
  event.preventDefault();
  isLoading.value = true;
  errorMessage.value = '';
  
  try {
    const response = await axios.post(`${API_URL}/login`, {
      username: username.value,
      password: password.value
    });
    
    const { jwtToken } = response.data;
    const userData = response.data.user || { username: username.value };
    
    handleLogin(jwtToken, userData);
    router.push('/');
    
  } catch (error) {
    errorMessage.value = error.response?.data?.message || 'Login failed. Please try again.';
  } finally {
    isLoading.value = false;
  }
};
</script>

<template>
<div className="vueCard">
    <form className="loginForm" @submit="submitLogin">
      <label>Username</label>
      <input 
        type="text" 
        v-model="username" 
        placeholder="Username" 
        required 
      /><br/><br/>
      
      <label>Password</label>
      <input 
        type="password" 
        v-model="password" 
        placeholder="Password" 
        required 
      /><br/><br/>
      
      <button type="submit" :disabled="isLoading">
        {{ isLoading ? 'Logging in...' : 'Login' }}
      </button>
      
      <p v-if="errorMessage" style="color: red;">{{ errorMessage }}</p>
    </form>
  </div>
</template>

<style scoped>
html {
  height: 100%;
}
body{
  margin:0;
  height:100%;
}
.body {
  height: 100%;
  display: flex;
  flex-direction: column;
}

.header {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  text-align: left;
  color: black;
  background-color: var(--buzzlite);
}

.header h1 {
     margin-left: 2rem;
}
.mainContent {
  background-color: var(--buzzGray);
  height:100%;
  display: flex;
  gap: 1rem;
  flex-direction: row;
  justify-content: space-evenly;
}
.vueCard {
padding: 1rem;
height: 10rem;
width: 15rem;
border-radius: 10%;
background-color: var(--buzzlite);
margin-top: 3rem;
display: flex;
justify-content: center;
align-items: center;
}
.loginForm {
  display: flex;
  flex-direction: column;
  align-items: center;
}
</style>