import './App.css';
import Login from './components/login';
import CreateAnAccount from './components/createAnAccount';
import UserProfile from './components/userProfile';
import PostsAndReplies from './components/postsAndReplies';
import ExplorePage from './components/explorePage';

function App() {
  return (
    <Routes>
      <Route path="/" element={<Login />}></Route>
      <Route path="createAnAccount" element={<CreateAnAccount/>}></Route>
      <Route path="userProfile" element={<UserProfile/>}></Route>
      <Route path="postsAndReplies" element={<PostsAndReplies/>}></Route>
      <Route path="explorePage" element={<ExplorePage/>}></Route>
    </Routes>
  );
}

export default App;
