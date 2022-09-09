import {Routes, Route} from 'react-router-dom';
import Login from './login/Login';
import CreateAnAccount from './pages/CreateAnAccount';
import Home from './pages/postsAndReplies';

function App() {
  return (
    <div className="App">
      <Routes>
      <Route path= "/" element={<Home />}></Route>
      <Route path='Login' element={<Login />}></Route>
      <Route path='CreateAnAccount' element={<CreateAnAccount />}></Route>
      </Routes>
    </div>
  );
}

export default App;