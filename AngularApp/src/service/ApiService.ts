import {Product} from '../models/Product';
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Page } from '../models/Page';
import {Pagination} from '../models/Pagination';

@Injectable({
    providedIn: 'root'
  })
export class ApiService {
    
    
    apiUrl = 'http://localhost:8080/api';
    constructor(private http: HttpClient) { }


    getProducts(pagination?: Pagination): Observable<Page<Product>> {
        let params;
        if (pagination) {
            params = new HttpParams()
            .set('pageNo', pagination.page ? pagination.page.toString() : '0')
            .set('size', pagination.size ? pagination.size.toString() : '30')
            .set('sortType', pagination.sortType ? pagination.sortType.toString() : 'ID')
            .set('order', pagination.order ? pagination.order.toString() : 'ASC');
            if(pagination.name) {
                params = params.set('name', pagination.name);
            }
        }
        return this.http.get<Page<Product>>(`${this.apiUrl}/products`, { params });
      }

    updateProduct(result: Product) {
        return this.http.post(`${this.apiUrl}`+`/product/update`, result);
    }
    
    addProduct(result: string) {
        return this.http.post(`${this.apiUrl}`+`/product/add`, result);
    }
}