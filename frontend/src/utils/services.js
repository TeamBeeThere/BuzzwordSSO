import axios from 'axios';

const API_URL = 'http://localhost:6767/sso'; // Replace when backend is ready
const TOKEN = 'eggplant-secret-token';

export const createAccount = async (employeeData) => {
  try {
    const response = await axios.post(`${API_URL}/createaccount`, employeeData, {
      headers: { token: TOKEN } 
    });
    return response.data;
  } catch (error) {
        console.error('Error adding employee:', error);
    return { data: null, error: error.message || 'Could not add employee.' };
  }
};