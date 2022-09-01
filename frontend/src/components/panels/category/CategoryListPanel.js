import { useEffect, useState } from "react"
import { CategoryPane } from "./CategoryPane"


export const CategoryListPanel = () => {
    const [categories, setCategories] = useState([]);
    const [loading, setLoading] = useState(false);

    useEffect(() => {
        fetchData();
    }, []);

    const fetchData = () => {

        setLoading(true);
        fetch("http://localhost:8081/categories")
            .then(response => response.json())
            .then(data => {
                setCategories(data);
                setLoading(false);
            })
            .catch(error => {
                console.log(error);
                setLoading(false);
            });
    }
    

    return (
        <>
            <article className='card border border-0'>
                <dl className='card-body'>
                    <dt className='card-title text-dark' > CATEGORIES ... </dt>
                    <section className='accordion accordion-flush ' id='accordionParent'>
                          
                        {loading ? <div> ... loading</div> :
                            categories.map((category) => {
                                return <CategoryPane key={category.id} 
                                    id={category.id}
                                    label={category.label}
                                    childCategories={category.childCategories}
                                    
                                    />
                        }) }
                    
                    </section>
                </dl>
            </article>
        </>
    )
}