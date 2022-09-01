import React, {useState, useEffect} from 'react';


export const TrendingListPanel = () => {
    const [trending, setTrending] = useState([]);
    const [loading, setLoading] = useState(false);

    useEffect(() => {
        fetchTrending();
    }, [])

    const fetchTrending = () => {

        setLoading(true);
        fetch("http://localhost:8081/categories/trending")
            .then(response => response.json())
            .then(data => {
                setTrending(data);
                setLoading(false);
                console.log(data);
            })
            .catch(error => {
                console.log(error);
                setLoading(false);
            });
    }

    
    return (
        <>
            <article className='card border border-0 '>
                <dl className='card-body border-bottom border-dark pb-1 mb-0'>
                    <dt className='card-title text-dark' > TRENDING ...</dt>
                    {/* <ul className=' d-flex flex-wrap justify-content-evenly list-group list-group-horizontal'> */}
                    <ul className='nav d-flex flex-wrap justify-content-evenly '>
                        {loading ? <div> ... loading </div> :
                            trending.map(trend => {
                                return <li key={trend.id} className='nav-item border m-1 p-1'>
                                            <small>{trend.label}</small>
                                        </li>
                            })
                        }
                    </ul>
                </dl>
            </article>
        </>
    )
}