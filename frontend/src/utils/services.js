import axios from 'axios';

import { API_URL, getAuthHeaders } from '../../config.js';


export const registerAccount = async (employeeData) => {
  try {
    const response = await axios.post(`${API_URL}/register`, employeeData, {
      headers: getAuthHeaders()
    });
    return response.data;
  } catch (error) {
    console.error('Error adding employee:', error);
    return { data: null, error: error.message || 'Could not add employee.' };
  }
};

export const viewAccount = async (employeeData) => {
  try {
    const response = await axios.get(`${API_URL}/account/${employeeData._id}`, employeeData, {
      headers: getAuthHeaders()
    });
    return response.data;
  } catch (error) {
        console.error('Error:', error);
    return { data: null, error: error.message || 'Could not Find Employee' };
  }
};

export const updateAccount = async (employeeData) => {
  try {
    const response = await axios.put(`${API_URL}/changepassword/${employeeData._id}`, employeeData, {
        headers: getAuthHeaders()
    });
    return response.data;
  } catch (error) {
        console.error('Error:', error);
    return { data: null, error: error.message || 'Could not update password' };
  }
};

export const delAccount = async (employeeData) => {
  try {
    const response = await axios.delete(`${API_URL}/deleteaccount/${employeeData._id}`, employeeData, {
         headers: getAuthHeaders()
    });
    return response.data;
  } catch (error) {
        console.error('Error:', error);
    return { data: null, error: error.message || 'Could not Find Employee' };
  }
};

export const loginUser = async (employeeData) => {
  try {
    const response = await axios.post(`${API_URL}/login`, employeeData, {
         headers: getAuthHeaders()
    });
    return response.data;
  } catch (error) {
        console.error('Error:', error);
    return { data: null, error: error.message || 'Credentials did not match a user in the system' };
  }
};


