import { useApiResources } from "../../../utils/useApiResources";
import { CategoryPane } from "./CategoryPane"


export const CategoryListPanel = () => {
    const [categories, loading] = useApiResources("categories");

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