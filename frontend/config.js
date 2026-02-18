export const API_URL = 'http://localhost:6767/sso';

export const getToken = () => localStorage.getItem('token');

export const getAuthHeaders = () => ({
  Authorization: `Bearer ${getToken()}`,
  'Content-Type': 'application/json',
});