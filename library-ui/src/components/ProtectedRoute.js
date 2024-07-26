import React from 'react';
import { Navigate } from 'react-router-dom';
import { jwtDecode } from 'jwt-decode';

const ProtectedRoute = ({ element: Component, roles, token }) => {
    if (token) {
        try {
            const decodedToken = jwtDecode(token);
            const currentTime = Date.now() / 1000;

            if (decodedToken.exp < currentTime) {
                localStorage.removeItem('token-library-app');
                return <Navigate to='/login' />;
            }

            if (!roles.includes(decodedToken.role)) {
                return <Navigate to='/unauthorized' />;
            }

            return <Component />;
        } catch (error) {
            localStorage.removeItem('token-library-app');
            return <Navigate to='/login' />;
        }
    }

    return <Navigate to='/login' />;
};

export default ProtectedRoute;
