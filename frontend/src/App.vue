<script setup>
import { RouterView } from 'vue-router';  
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
