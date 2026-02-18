import { createRouter, createWebHistory } from 'vue-router'
import Eggplant from '@/vues/eggplant/Eggplant.vue'
import Hub from '@/vues/hub/Hub.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    { path: '/', name: 'hub', component: Hub },
    { path: '/eggplant', name: 'eggplant', component: Eggplant },

  ],
})

export default router
