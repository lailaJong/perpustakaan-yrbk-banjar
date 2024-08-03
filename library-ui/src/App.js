import React, { useEffect, useState } from 'react';
import { BrowserRouter as Router, Route, Routes, Navigate } from 'react-router-dom';
import { CssBaseline, Box, CircularProgress } from '@mui/material';
import { styled } from '@mui/material/styles';
import { jwtDecode } from 'jwt-decode';

/* Admin */
import Sidebar from './components/Sidebar';
import Dashboard from './pages/admin/Dashboard';
import BooksStock from './pages/admin/BooksStock';
import Authors from './pages/admin/Authors';
import Publishers from './pages/admin/Publishers';
import BooksCategories from './pages/admin/BooksCategories';
import Bookshelves from './pages/admin/Bookshelves';
import BorrowingTransactionHistory from './pages/admin/BorrowingTransactionHistory';

/* User */
import UserSidebar from './components/UserSidebar';
import UserDashboard from './pages/user/UserDashboard';
import UserProfile from './pages/user/UserProfile';
import UserCatalog from './pages/user/UserCatalog';
import UserBorrowingCollection from './pages/user/UserBorrowingCollection';
import UserOrderingCollection from './pages/user/UserOrderingCollection';
import UserBorrowingHistory from './pages/user/UserBorrowingHistory';

/* General */
import Welcome from './pages/Welcome';
import Login from './pages/Login';
import ProtectedRoute from './components/ProtectedRoute';
import Unauthorized from './pages/Unauthorized';
import TermsAndConditions from './pages/TermsAndConditions';
import SignUp from './pages/SignUp';
import UserUpdatePassword from './pages/user/UserUpdatePassword';
import UserUpdateProfile from './pages/user/UserUpdateProfile';
import UserCatalogDetail from './pages/user/UserCatalogDetail';
import CollectionList from './pages/admin/CollectionList';
import CollectionDetail from './pages/admin/CollectionDetail';
import Header from './components/Header';
import AddBook from './pages/admin/AddBook';
import EditBook from './pages/admin/EditBook';
import UsersList from './pages/admin/UsersList';
import UserDetail from './pages/admin/UserDetail';
import BorrowingTransactionList from './pages/admin/BorrowingTransactionList';
import LateReturnList from './pages/admin/LateReturnList';
import AddBorrowingTransaction from './pages/admin/AddBorrowingTransaction';
import AddUser from './pages/admin/AddUser';

const MainContent = styled(Box)(({ open }) => ({
  flexGrow: 1,
  padding: '24px',
  marginLeft: open ? 240 : 60,
  transition: 'margin-left 0.3s',
}));

const App = () => {
  const [token, setToken] = useState(localStorage.getItem('token-library-app'));
  const [userId, setUserId] = useState('');
  const [userRole, setUserRole] = useState('');
  const [open, setOpen] = useState(true);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    if (token) {
      try {
        const decodedToken = jwtDecode(token);
        setUserId(decodedToken.id);
        setUserRole(decodedToken.role);
      } catch (error) {
        console.error('Invalid Token');
        setToken(null);
        localStorage.removeItem('token-library-app');
      }
      setLoading(false);
    }
  }, [token, userRole])

  const handleDrawerToggle = () => {
    setOpen(!open);
  };

  if (token && loading) {
    return (
      <Box
        sx={{
          display: 'flex',
          justifyContent: 'center',
          alignItems: 'center',
          height: '100vh',
        }}
      >
        <CircularProgress />
      </Box>
    );
  }

  return (
    <Router>
      <CssBaseline />
      <Routes>
        <Route path='/welcome' element={<Welcome setToken={setToken} />} />
        <Route path='/login' element={<Login setToken={setToken} setLoading={setLoading} />} />
        <Route path='/terms-and-conditions' element={<TermsAndConditions />} />
        <Route path='/signup' element={<SignUp />} />
        <Route path='/' element={userRole === 'admin' ? <Navigate to='/admin/dashboard' /> : userRole === 'user' ? <Navigate to='/user/dashboard' /> : <Navigate to='/welcome' />} />
        <Route
          path='/admin/dashboard'
          element={<ProtectedRoute element={() => <ProtectedLayout component={<Dashboard />} open={open} handleDrawerToggle={handleDrawerToggle} userRole={userRole} />} roles={['admin']} token={token} />}
        />
        <Route
          path='/admin/collection'
          element={<ProtectedRoute element={() => <ProtectedLayout component={<CollectionList />} open={open} handleDrawerToggle={handleDrawerToggle} userRole={userRole} />} roles={['admin']} token={token} />}
        />
        <Route
          path='/admin/collection/detail/:id'
          element={<ProtectedRoute element={() => <ProtectedLayout component={<CollectionDetail />} open={open} handleDrawerToggle={handleDrawerToggle} userRole={userRole} />} roles={['admin']} token={token} />}
        />
        <Route
          path='/admin/collection/add'
          element={<ProtectedRoute element={() => <ProtectedLayout component={<AddBook />} open={open} handleDrawerToggle={handleDrawerToggle} userRole={userRole} />} roles={['admin']} token={token} />}
        />
        <Route
          path='/admin/collection/edit/:id'
          element={<ProtectedRoute element={() => <ProtectedLayout component={<EditBook />} open={open} handleDrawerToggle={handleDrawerToggle} userRole={userRole} />} roles={['admin']} token={token} />}
        />
        <Route
          path='/admin/users'
          element={<ProtectedRoute element={() => <ProtectedLayout component={<UsersList />} open={open} handleDrawerToggle={handleDrawerToggle} userRole={userRole} />} roles={['admin']} token={token} />}
        />
        <Route
          path='/admin/users/detail/:id'
          element={<ProtectedRoute element={() => <ProtectedLayout component={<UserDetail />} open={open} handleDrawerToggle={handleDrawerToggle} userRole={userRole} />} roles={['admin']} token={token} />}
        />
        <Route
          path='/admin/users/add'
          element={<ProtectedRoute element={() => <ProtectedLayout component={<AddUser />} open={open} handleDrawerToggle={handleDrawerToggle} userRole={userRole} />} roles={['admin']} token={token} />}
        />
        <Route
          path='/admin/borrowing-transaction-list'
          element={<ProtectedRoute element={() => <ProtectedLayout component={<BorrowingTransactionList />} open={open} handleDrawerToggle={handleDrawerToggle} userRole={userRole} />} roles={['admin']} token={token} />}
        />
        <Route
          path='/admin/borrowing-transaction-list/add'
          element={<ProtectedRoute element={() => <ProtectedLayout component={<AddBorrowingTransaction />} open={open} handleDrawerToggle={handleDrawerToggle} userRole={userRole} />} roles={['admin']} token={token} />}
        />
        <Route
          path='/admin/late-return-list'
          element={<ProtectedRoute element={() => <ProtectedLayout component={<LateReturnList />} open={open} handleDrawerToggle={handleDrawerToggle} userRole={userRole} />} roles={['admin']} token={token} />}
        />
        <Route
          path='/admin/books-stock'
          element={<ProtectedRoute element={() => <ProtectedLayout component={<BooksStock />} open={open} handleDrawerToggle={handleDrawerToggle} userRole={userRole} />} roles={['admin']} token={token} />}
        />
        <Route
          path='/admin/authors'
          element={<ProtectedRoute element={() => <ProtectedLayout component={<Authors />} open={open} handleDrawerToggle={handleDrawerToggle} userRole={userRole} />} roles={['admin']} token={token} />}
        />
        <Route
          path='/admin/publishers'
          element={<ProtectedRoute element={() => <ProtectedLayout component={<Publishers />} open={open} handleDrawerToggle={handleDrawerToggle} userRole={userRole} />} roles={['admin']} token={token} />}
        />
        <Route
          path='/admin/books-categories'
          element={<ProtectedRoute element={() => <ProtectedLayout component={<BooksCategories />} open={open} handleDrawerToggle={handleDrawerToggle} userRole={userRole} />} roles={['admin']} token={token} />}
        />
        <Route
          path='/admin/bookshelves'
          element={<ProtectedRoute element={() => <ProtectedLayout component={<Bookshelves />} open={open} handleDrawerToggle={handleDrawerToggle} userRole={userRole} />} roles={['admin']} token={token} />}
        />
        <Route
          path='/admin/borrowing-transaction-history'
          element={<ProtectedRoute element={() => <ProtectedLayout component={<BorrowingTransactionHistory />} open={open} handleDrawerToggle={handleDrawerToggle} userRole={userRole} />} roles={['admin']} token={token} />}
        />
        {/* User Route */}
        <Route
          path='/user/dashboard'
          element={<ProtectedRoute element={() => <ProtectedLayout component={<UserDashboard />} open={open} handleDrawerToggle={handleDrawerToggle} userRole={userRole} />} roles={['user']} token={token} />}
        />
        <Route
          path='/user/profile'
          element={<ProtectedRoute element={() => <ProtectedLayout component={<UserProfile userId={userId} />} open={open} handleDrawerToggle={handleDrawerToggle} userRole={userRole} />} roles={['user']} token={token} />}
        />
        <Route
          path='/user/update-password'
          element={<ProtectedRoute element={() => <ProtectedLayout component={<UserUpdatePassword />} open={open} handleDrawerToggle={handleDrawerToggle} userRole={userRole} />} roles={['user']} token={token} />}
        />
        <Route
          path='/user/update-profile'
          element={<ProtectedRoute element={() => <ProtectedLayout component={<UserUpdateProfile />} open={open} handleDrawerToggle={handleDrawerToggle} userRole={userRole} />} roles={['user']} token={token} />}
        />
        <Route
          path='/user/catalog'
          element={<ProtectedRoute element={() => <ProtectedLayout component={<UserCatalog />} open={open} handleDrawerToggle={handleDrawerToggle} userRole={userRole} />} roles={['user']} token={token} />}
        />
        <Route
          path='/user/catalog/detail/:id'
          element={<ProtectedRoute element={() => <ProtectedLayout component={<UserCatalogDetail />} open={open} handleDrawerToggle={handleDrawerToggle} userRole={userRole} />} roles={['user']} token={token} />}
        />
        <Route
          path='/user/borrowing-collection'
          element={<ProtectedRoute element={() => <ProtectedLayout component={<UserBorrowingCollection />} open={open} handleDrawerToggle={handleDrawerToggle} userRole={userRole} />} roles={['user']} token={token} />}
        />
        <Route
          path='/user/ordering-collection'
          element={<ProtectedRoute element={() => <ProtectedLayout component={<UserOrderingCollection />} open={open} handleDrawerToggle={handleDrawerToggle} userRole={userRole} />} roles={['user']} token={token} />}
        />
        <Route
          path='/user/borrowing-history'
          element={<ProtectedRoute element={() => <ProtectedLayout component={<UserBorrowingHistory />} open={open} handleDrawerToggle={handleDrawerToggle} userRole={userRole} />} roles={['user']} token={token} />}
        />
        <Route path='/unauthorized' element={<Unauthorized />} />
      </Routes>
    </Router>
  );
};

const ProtectedLayout = ({ component: Component, open, handleDrawerToggle, userRole }) => {
  return (
    <Box sx={{ display: 'flex' }}>
      {userRole === 'admin' ?
        <Sidebar open={open} handleDrawerToggle={handleDrawerToggle} userRole={userRole} />
        : <UserSidebar open={open} handleDrawerToggle={handleDrawerToggle} />}
      <MainContent open={open}>
        <Header title={userRole === 'admin' ? 'Dashboard' : 'Keanggotaan Online'} />
        {Component}
      </MainContent>
    </Box>
  );
};

export default App;