<?php

namespace App\Repository;

use Illuminate\Contracts\Container\BindingResolutionException;

abstract class Repository implements RepositoryInterface
{
    protected $model;

    /**
     * @throws BindingResolutionException
     */
    public function __construct()
    {
        $this->setModel();
    }

    abstract public function getModel();

    /**
     * @throws BindingResolutionException
     */
    private function setModel(): void
    {
        $this->model = app()->make(
            $this->getModel()
        );
    }

    public function find(int $id)
    {
        return $this->model->find($id);
    }

    public function create(array $attributes)
    {
        return $this->model->create($attributes);
    }

    public function update(int $id, array $attributes)
    {
        $result = $this->find($id);
        if ($result) {
            $result->update($attributes);
            return $result;
        }
        return false;
    }

    public function delete(int $id): bool
    {
        $result = $this->find($id);
        if ($result) {
            $result->delete();
            return true;
        }
        return false;
    }
}