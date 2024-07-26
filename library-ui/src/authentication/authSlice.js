// authSlice.js
import { createSlice } from '@reduxjs/toolkit';
import { jwtDecode } from 'jwt-decode';

const initialState = {
    token: null,
    userRole: null,
    //   permissions: [],
};

export const authSlice = createSlice({
    name: 'auth',
    initialState,
    reducers: {
        setToken: (state, action) => {
            state.token = action.payload;
            const decoded = jwtDecode(action.payload);
            state.userRole = decoded.role;
            //   state.permissions = decoded.permissions;
        },
        clearAuth: (state) => {
            state.token = null;
            state.userRole = null;
            //   state.permissions = [];
        },
    },
});

export const { setToken, clearAuth } = authSlice.actions;
export default authSlice.reducer;