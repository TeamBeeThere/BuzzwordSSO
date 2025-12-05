<script setup>
import {defineEmits} from 'vue';
const emit = defineEmits(['updateDisplayUser']);

let user = {
  firstName: 'Bill',
  lastName: 'Murray',
}; 

let displayName = user
  ? user.firstName.charAt(0).toUpperCase() + user.lastName.charAt(0).toUpperCase()
  : '';
</script>

<template>
<div className="vueCard">
    <button @click="emit('updateDisplayUser', false)">Back to Main</button>
    <div className="profile">
       <div v-if="user" className="displayUser" > {{displayName}}  </div>
       <div className="infoSection">
         <p> First Name: {{user.firstName}}</p>
         <p> Last Name: {{user.lastName}}</p>
         <p> Location: {{user.location }}</p>
         <p> Department: {{user.department }}</p>
         <p> Title: {{user.title }}</p>
       </div>
       <form class="profileForm">
         <label>Update Password</label><input type="password" placeholder="New Password" /><br/><br/>
         <button type="submit">Update Password</button>
        
       </form>


    </div>



</div>
</template>

<style scoped>
.vueCard {
padding: 1rem;
height: 10rem;
width: 25rem;
border-radius: 10%;
background-color: var(--buzzlite);
margin-top: 3rem;
}
.profile {
  margin-top: 1rem;
  display: flex;
  flex-direction: column;
justify-content: center;
align-items: center;
}
.displayUser {
  margin-right: 2rem;
  font-weight: bold;  
  background-color: var(--buzzGray);
  color: white;
  padding: 1rem 1rem;
  border-radius: 50%;
}
.infoSection {
  display: flex;
  flex-direction: column;
  justify-content: center;
text-align: left;
}
.profileForm {
  display: flex;
  flex-direction: row;
  gap: 5px;
  justify-content: center;
  flex-wrap: wrap;
  align-items: center;
}
</style>