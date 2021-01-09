import './styles.css';
import StepsHeader from './StepsHeader';
import ProductsList from './ProductsList';
import OrderLocation from './OrderLocation';
import OrderSummary from './OrderSummary';
import { toast } from 'react-toastify';
import { OrderLocationData, Product } from './types';
import { useEffect, useState } from 'react';
import { fetchProducts, saveOrder } from '../api';
import { checkIsSelected } from './helpers';



function Orders() {
    const [products, setProducts] = useState<Product[]>([]);
    const [selectedProducts, setSelectedProducts] = useState<Product[]>([]);
    const [orderLocation, setOrderLocation] = useState<OrderLocationData>();
    const totalPrice = selectedProducts.reduce((sum, item) => {
        return sum + item.price;
    }, 0);

    useEffect(() =>{
        fetchProducts()
        .then(response => setProducts(response.data))
        .catch(() => toast.warning('Erro ao listar produtos'))
    }, [])

    const handleSelectProduct = (product: Product) => {
        const isAlreadySelected = checkIsSelected(selectedProducts, product);
      
        if (isAlreadySelected) {
            const selected = selectedProducts.filter(item => item.id !== product.id);
            setSelectedProducts(selected);
        } else {
            setSelectedProducts(previous => [...previous, product]);
        }
      }

    const handleSubmit = () => {
        const productsIds = selectedProducts.map(({ id }) => ({ id }));
        const payload = {
          ...orderLocation!,
          products: productsIds
        }
        
        if (payload.products.length === 0){
            toast.warning('Selecione os produtos');
        }
        else if (payload.address === undefined){
            toast.warning('Digite o endereço da entrega');
        }
        else {
            saveOrder(payload).then(() => {
                toast.error('Pedido enviado com sucesso!');
                setSelectedProducts([]);
              })
                .catch(() => {
                  toast.warning('Erro ao enviar pedido');
                  })
        }
      }

    return (
        <>
            <div className="orders-container">
                <StepsHeader />
                <ProductsList  
                    products={products}
                    onSelectProduct={handleSelectProduct}
                    selectedProducts={selectedProducts}/>
                <OrderLocation onChangeLocation={location => setOrderLocation(location)}/>
                <OrderSummary amount={selectedProducts.length} 
                    totalPrice={totalPrice}
                    onSubmit={handleSubmit} />
            </div>
        </>
    )
}

export default Orders;