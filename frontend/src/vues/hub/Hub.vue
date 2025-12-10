<script setup>
import { ref } from 'vue';
import Login from './Login.vue'
import AppList from './AppList.vue'
import Profile from './Profile.vue'


let user = {
  firstName: 'Bill',
  lastName: 'Murray',
}; 

let displayName = user
  ? user.firstName.charAt(0).toUpperCase() + user.lastName.charAt(0).toUpperCase()
  : '';

let displayUser = ref(false);

</script>

<template>
  <div className="body">
  <div className="header">
    <h1>Buzzword Software Solutions</h1>
    <div v-if="user" className="displayUser" @click="displayUser = true"> {{displayName}}  </div>
</div>

<div className="mainContent">

  <Login v-if="!user"/> 
  <AppList v-if="user && !displayUser"/>
  <Profile v-if="user && displayUser"  @updateDisplayUser="displayUser = $event"
/>

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
   display: flex;
     flex-direction: row;
     justify-content: space-between;
      align-items: center;
}

.header h1 {
     margin-left: 2rem;
}

.displayUser {
  margin-right: 2rem;
  font-weight: bold;  
  background-color: var(--buzzGray);
  color: white;
  padding: 0.5rem .5rem;
  border-radius: 50%;
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
height: 25rem;
width: 20rem;
border-radius: 10%;
background-color: var(--buzzlite);
margin-top: 3rem;
}
</style>
