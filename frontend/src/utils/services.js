import axios from 'axios';

const API_URL = 'database URL HERE'; // Replace when backend is ready
const TOKEN = 'eggplant-secret-token';

export const addEmployee = async (employeeData) => {
  try {
    const response = await axios.post(`${API_URL}/employees`, employeeData, {
      headers: { token: TOKEN } 
    });
    return response.data;
  } catch (error) {
        console.error('Error adding employee:', error);
    return { data: null, error: error.message || 'Could not add employee.' };
  }
};