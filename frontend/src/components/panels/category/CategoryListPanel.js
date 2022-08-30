import { CategoryPane } from "./CategoryPane"



export const CategoryListPanel = () => {

    const categories = [ 
        {id: 1, parentCategory: "A", subCategory: [{id: "1.1", caption: "A1"}, {id: "1.2", caption: "A2"}]},
        {id: 2, parentCategory: "B", subCategory: [{id: "2.1", caption: "B1"}, {id: "1.2", caption: "B2"}]},
        {id: 3, parentCategory: "C", subCategory: [{id: "3.1", caption: "C1"}, {id: "1.3", caption: "C2"}]},
    ]    

    return (
        <>
            <article className='card border border-0'>
                <dl className='card-body'>
                    <dt className='card-title text-dark' > CATEGORIES ... </dt>
                    <section className='accordion accordion-flush ' id='accordionParent'>

                        {categories.map((category) => {
                            return <CategoryPane key={category.id} 
                                id={category.id}
                                parentCategory={category.parentCategory}
                                subCategory={category.subCategory}
                                
                                />
                        })}
                    
                    </section>
                </dl>
            </article>
        </>
    )
}