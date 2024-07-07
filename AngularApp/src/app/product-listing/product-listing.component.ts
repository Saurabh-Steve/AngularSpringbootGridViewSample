import { Component, OnInit } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { Page } from '../../models/Page'
import { Product } from '../../models/Product';
import { ApiService } from '../../service/ApiService';
import { catchError, of } from 'rxjs';

@Component({
  selector: 'app-product-listing',
  templateUrl: './product-listing.component.html',
  styleUrls: ['./product-listing.component.css']
})
export class ProductListingComponent implements OnInit {
 
  displayedColumns: string[] = ['id', 'name', 'description', 'rating', 'price', 'image'];
  products: Product[] = [];
  page?: Page<Product>;
  pageSize = 3;
  currentPage = 0;
  searchQuery = '';


  constructor(private apiService: ApiService) {
  }

  ngOnInit(): void {
    this.loadProducts();
  }

  loadProducts(n? : string, sortType?: string, direction?: string) {
    const type = (sortType == undefined) ? 'id' : sortType;
    const orderType = (direction == undefined) ? 'ASC' : direction;
    this.apiService.getProducts({ name: n,
       page: this.currentPage,
        size: this.pageSize, 
        sortType: type,
         order: orderType})
    .pipe(catchError((error: HttpErrorResponse) => {
        console.log(error.message);
        return of(null); 
      })
    )
    .subscribe((data: Page<Product> | null) => {
      if (data) { 
        this.page = data;
        this.products = data.content;
      } else {
        this.products = [];
      }
    });
  }

   onClickSearch() {
    this.currentPage = 0;
    this.loadProducts(this.searchQuery);
  }

  onSelectionChange(event: Event) {
    const selectedValue = (event.target as HTMLSelectElement).value;
    let sortType = 'ID';
    let order = 'ASC';
    switch(selectedValue)
    {
      case '1':
        sortType = 'ID';
        order = 'ASC';
        break;
      case '2':
        sortType = 'ID';
        order = 'DESC';
        break;
      case '3':
        sortType = 'PRICE';
        order = 'ASC';
        break;
      case '4':
        sortType = 'PRICE';
        order = 'DESC';
        break;
      case '5':
        sortType = 'RATING';
        order = 'ASC';
        break;
      case '6':
        sortType = 'RATING';
        order = 'DESC';
        break;
      case '7':
        sortType = 'NAME';
        order = 'ASC';
        break;       
      case '8':
        sortType = 'NAME';
        order = 'DESC';
        break;
    }

    this.loadProducts(this.searchQuery, sortType, order);
  }
}