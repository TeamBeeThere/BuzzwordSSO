<script setup>
import { ref, provide, inject, onMounted } from 'vue';
import { RouterView } from 'vue-router';
import axios from 'axios';
import { API_URL, getAuthHeaders } from '../config.js';

const user = ref(null);
const token = ref(null);
const $cookies = inject('$cookies');

const handleLogin = (jwtToken, userData) => {
  token.value = jwtToken;
  user.value = userData;
  
  if ($cookies) {
    $cookies.set('eggplant_user_token', jwtToken);
  }

  axios.defaults.headers.common['Authorization'] = `Bearer ${jwtToken}`;
};

const handleLogout = () => {
  user.value = null;
  token.value = null;
  
  if ($cookies) {
    $cookies.remove('eggplant_user_token');
  }
  
  delete axios.defaults.headers.common['Authorization'];
};

onMounted(() => {

  if ($cookies) {
    const savedToken = $cookies.get('eggplant_user_token');
    if (savedToken) {
      token.value = savedToken;
      axios.defaults.headers.common['Authorization'] = `Bearer ${savedToken}`;
    }
  }
});

provide('user', user);
provide('token', token);
provide('handleLogin', handleLogin);
provide('handleLogout', handleLogout);
</script>

<template>
  <router-view />
</template>

<style>
:root {
/* Hub Color Palette */
  --buzzlite: #f1e19f;
  --buzzGray: #373737;
  /* Eggplant Color Palette */
  --green: #349948;
  --greenlite: #c2e0c8;
  --purple: #43217e;
  --purplelite: #ebd6e7;
  --blue: #345299;
  --orange: #997a34;
}
html {
  height: 100%;
}
body{
  margin:0;
  height:100%;
}
#app{
  height:100%;
}
</style>
