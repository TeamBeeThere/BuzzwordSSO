<script setup>
import { ref, watch, onMounted, provide } from 'vue';
import { RouterView } from 'vue-router';
import axios from 'axios';

const API_URL = 'http://localhost:6767/sso';

const savedUser = localStorage.getItem('user');
const user = ref(savedUser ? JSON.parse(savedUser) : null);

const token = ref(localStorage.getItem('token'));

watch([token, user], () => {
  if (token.value && !user.value) {
    // token verification
    axios.get(`${API_URL}/user`, {
      headers: {
        'Authorization': `Bearer ${token.value}`
      }
    })
    .then(response => {
      if (response.data.id) {
        user.value = response.data;
        localStorage.setItem('user', JSON.stringify(response.data));
      } else {
        localStorage.removeItem('token');
        localStorage.removeItem('user');
        token.value = null;
      }
    })
    .catch(() => {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      token.value = null;
    });
  }
}, { immediate: true });

const handleLogin = (newToken, userData) => {
  localStorage.setItem('token', newToken);
  localStorage.setItem('user', JSON.stringify(userData));
  token.value = newToken;
  user.value = userData;
};

const handleLogout = () => {
  localStorage.removeItem('token');
  localStorage.removeItem('user');
  token.value = null;
  user.value = null;
};

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
