<script setup>
import { inject } from 'vue';
import { useRouter } from 'vue-router';

const router = useRouter();
const user = inject('user');
const token = inject('token');

const departmentNames = {
  1: 'Sales',
  2: 'Information Technology',
  3: 'Legal',
  4: 'Hr'
};

const titleNames = {
  1: 'Aide',
  2: 'Developer',
  3: 'Sales Agent',
  4: 'Manager'
};

let defaultPath = 'http://localhost:5173/'

let apps = [
  { name: 'BeeThere', image: 'BeeThere.png', color: 'var(--greenlite)', path: defaultPath }, 
  { name: 'SaleBoat', image: 'saleBoat.png', department: 'sales', color: 'var(--purplelite)', path: defaultPath },
  { name: 'EggPlant', image: 'htmlicon.svg', department: 'hr', title: 'manager', color: 'var(--purple)', path: '/eggplant' },
  { name: 'SwaB', image: 'swab.png', color: 'var(--blue)', path: defaultPath },
  { name: '', image: '', color: 'var(--buzzlite)', path: '' },
  { name: '', image: '', color: 'var(--buzzlite)', path: '' },
  { name: '', image: '', color: 'var(--buzzlite)', path: '' },
  { name: '', image: '', color: 'var(--buzzlite)', path: '' },
];

const navigateToApp = (path) => {
  let finalPath = path;
  
  if (path.startsWith('http') && token && token.value) {
    finalPath = `${path}?token=${encodeURIComponent(token.value)}`;
    window.location.href = finalPath;
  } else if (path.startsWith('http')) {
    window.location.href = path;
  } else {
    router.push(path);
  }
};

const canAccessApp = (app) => {

  if (!app.department && !app.title) {
    console.log('App has no restrictions, showing:', app.name);
    return true;
  }

  if (!user || !user.value) {
    console.log('User not logged in, hiding restricted app:', app.name);
    return false;
  }

  let userDept = '';
  let userTitle = '';

  if (typeof user.value.department === 'number') {
    userDept = (departmentNames[user.value.department] || '').toLowerCase();
  } else if (typeof user.value.department === 'string') {
    userDept = user.value.department.toLowerCase();
  }

  if (typeof user.value.title === 'number') {
    userTitle = (titleNames[user.value.title] || '').toLowerCase();
  } else if (typeof user.value.title === 'string') {
    userTitle = user.value.title.toLowerCase();
  }

  const deptMatch = !app.department || app.department.toLowerCase() === userDept;
  const titleMatch = !app.title || app.title.toLowerCase() === userTitle;

  return deptMatch && titleMatch;
};
</script>


<template>
  <div class="mainCard">
    <div 
      v-for="(app, index) in apps" 
      :key="index"
      class="appCard" 
      @click="navigateToApp(app.path)" :style="{ backgroundColor: app.color }"
       v-show="canAccessApp(app)"
      >
     <img v-if="app.image && app.name" :src="`/src/assets/${app.image}`" :alt="app.name" width="100" height="100" />
      <p>{{ app.name }}</p>
  
    </div>
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
.mainCard {
  background-color: var(--buzzGray);
  height:100%;
  width: 80%;
  display: flex;
  gap: .5rem;
  flex-direction: row;
  flex-wrap: wrap;
  justify-content: space-evenly;
}
.appCard {
    height: 10rem;
    width: 10rem;
  padding: 1rem;
  margin: 1rem;
  border-radius: 8px;
  background-color: white;
  cursor: pointer;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  transition: transform 0.2s;
}

.appCard:hover {
  transform: scale(1.05);
}

.loginForm {
  display: flex;
  flex-direction: column;
  align-items: center;
}
</style>