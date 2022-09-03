import {Routes, Route} from 'react-router-dom';
import Login from './pages/Login';
import Home from './pages/postsAndReplies';

function App() {
  return (
    <div className="App">
      <Routes>
      <Route path= "/" element={<Home />}></Route>
      <Route path='Login' element={<Login />}></Route>
      </Routes>
    </div>
  );
}

export default App;